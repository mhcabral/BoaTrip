<?php
namespace common\models;
 
use Yii;
use yii\base\NotSupportedException;
use yii\behaviors\TimestampBehavior;
use yii\db\ActiveRecord;
use yii\db\Expression;
use yii\web\IdentityInterface;
use yii\helpers\Security;
 
        /**
         * User model
         *
         * @property integer $id
         * @property string $username
         * @property string $password_hash
         * @property string $password_reset_token
         * @property string $email
         * @property string $auth_key
         * @property integer $usuario_funcao_id
         * @property integer $usuario_status_id
         * @property integer $usuario_tipo_id
         * @property integer $created_at
         * @property integer $updated_at
         * @property string $password write-only password
         */
 
class User extends ActiveRecord implements IdentityInterface
{
  	
     const STATUS_ACTIVE = 1;
 
    
     public static function tableName()
     {
         return 'user';
     }
 
        /**
        * behaviors
        */
 
    public function behaviors()
    {
        return [
            'timestamp' => [
            'class' => 'yii\behaviors\TimestampBehavior',
            'attributes' => [
                                ActiveRecord::EVENT_BEFORE_INSERT => ['created_at', 'updated_at'],
                                ActiveRecord::EVENT_BEFORE_UPDATE => ['updated_at'],
                            ],
            'value' => new Expression('NOW()'),
                           ],
               ];
    }
 
        /**
        * validation rules
        */
 
    public function rules()
    {
        return [
 
           ['usuario_status_id', 'default', 'value' => self::STATUS_ACTIVE],
           ['usuario_funcao_id', 'default', 'value' => 1],
           ['usuario_tipo_id', 'default', 'value' => 1],
 
           ['username', 'filter', 'filter' => 'trim'],
           ['username', 'required'],
           ['username', 'unique'],
           ['username', 'string', 'min' => 2, 'max' => 255],
 
           ['email', 'filter', 'filter' => 'trim'],
           ['email', 'required'],
           ['email', 'email'],
           ['email', 'unique'],
           
	   ];
    }
    
        /* Your model attribute labels */
 
    public function attributeLabels() 
    {
     return [
            /* Your other attribute labels */
            ];
    }
 
        /**
        * @findIdentity
        */
 
    public static function findIdentity($id)
    {
        return static::findOne(['id' => $id, 'usuario_status_id' => self::STATUS_ACTIVE]);
    }
 
         /**
     * @inheritdoc
     */
    public static function findIdentityByAccessToken($token, $type = null)
    {
        throw new NotSupportedException('"findIdentityByAccessToken" is not implemented.');
    }
 
        /**
        * Finds user by username
        * broken into 2 lines to avoid wordwrapping * @param string $username
        * @return static|null
        */
   
    public static function findByUsername($username)
    {
        return static::findOne(['username' => $username, 'usuario_status_id' => self::STATUS_ACTIVE]);
    }
 
        /**
        * Finds user by password reset token
        *
        * @param string $token password reset token
        * @return static|null
        */
 
 
 
    public static function findByPasswordResetToken($token)
    {
        if (!static::isPasswordResetTokenValid($token)) {
            return null;
        }
 
        return static::findOne([
            'password_reset_token' => $token,
            'usuario_status_id' => self::STATUS_ACTIVE,
        ]);
    }
 
    /**
     * Finds out if password reset token is valid
     *
     * @param string $token password reset token
     * @return boolean
     */
 
    public static function isPasswordResetTokenValid($token)
    {
        if (empty($token)) {
            return false;
        }
        $expire = Yii::$app->params['user.passwordResetTokenExpire'];
        $parts = explode('_', $token);
        $timestamp = (int) end($parts);
        return $timestamp + $expire >= time();
    }
 
        /**
        * @getId
        */
 
    public function getId()
    {
        return $this->getPrimaryKey();
    }
 
        /**
        * @getAuthKey
        */
 
    public function getAuthKey()
    {
        return $this->auth_key;
    }
 
        /**
        * @validateAuthKey
        */
 
    public function validateAuthKey($authKey)
    {
        return $this->getAuthKey() === $authKey;
    }
 
        /**
        * Validates password
        *
        * @param string $password password to validate
        * @return boolean if password provided is valid for current user
        */
 
    public function validatePassword($password)
    {
        return Yii::$app->security->validatePassword($password, $this->password_hash);
    }
 
        /**
        * Generates password hash from password and sets it to the model
        *
        * @param string $password
        */
 
    public function setPassword($password)
    {
         $this->password_hash = Yii::$app->security->generatePasswordHash($password);
    }
 
        /**
        * Generates "remember me" authentication key
        */
 
    public function generateAuthKey()
    {
        $this->auth_key = Yii::$app->security->generateRandomString();
    }
 
        /**
        * Generates new password reset token
        * broken into 2 lines to avoid wordwrapping
        */
 
    public function generatePasswordResetToken()
    {
        $this->password_reset_token = Yii::$app->security->generateRandomString()  . '_' . time();
    }
 
        /**
        * Removes password reset token
        */
 
    public function removePasswordResetToken()
    {
        $this->password_reset_token = null;
    }
    
	public function getPerfil()
	{
		return $this->hasOne(Usuario_Perfil::className(), ['user_id' => 'id']);
	}
}