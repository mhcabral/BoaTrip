<?php

namespace app\models;

use Yii;
use yii\db\ActiveRecord;
use app\models\User;
use yii\helpers\Url;
use yii\helpers\Html;
use yii\helpers\ArrayHelper;
use yii\db\Expression;

/**
 * This is the model class for table "profile".
 *
 * @property string $id
 * @property string $user_id
 * @property string $first_name
 * @property string $last_name
 * @property string $cpf
 * @property string $rg
 * @property string $endereco
 * @property string $cep
 * @property string $telefone
 * @property string $birthdate
 * @property string $created_at
 * @property string $updated_at
 * @property integer $genero_id
 *
 * @property Genero $genero
 * @property User $user
 */
class Profile extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'profile';
    }
    
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
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['user_id', 'cpf', 'genero_id'], 'required'],
            [['user_id', 'genero_id'], 'integer'],
            [['first_name', 'last_name'], 'string', 'max' => 60],
            [['birthdate', 'created_at', 'updated_at'], 'safe'],
            [['cpf'], 'string', 'max' => 11],
            [['rg', 'telefone'], 'string', 'max' => 20],
            [['endereco'], 'string', 'max' => 60],
            [['cep'], 'string', 'max' => 8],
        		
        	[['genero_id'],'in', 'range'=>array_keys($this->getGeneroList())],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'user_id' => 'User ID',
            'first_name' => 'Nome',
            'last_name' => 'Sobrenome',
            'cpf' => 'CPF',
            'rg' => 'RG',
            'endereco' => 'Endereço',
            'cep' => 'Cep',
            'telefone' => 'Telefone',
            'birthdate' => 'Data Nascimento',
            'created_at' => 'Criado Em',
            'updated_at' => 'Atualizado Em',
            'genero_id' => 'Gênero',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getGenero()
    {
        return $this->hasOne(Genero::className(), ['id' => 'genero_id']);
    }
  
    /**
     * @return \yii\db\ActiveQuery
     */
    
    public function getGeneroNome()
    {
    	return $this->genero->genero_name;
    }
    
    /**
     * get list of genders for dropdown
     */
    
    public static function getGeneroList()
    {
    
    	$droptions = Genero::find()->asArray()->all();
    	return ArrayHelper::map($droptions, 'id', 'genero_nome');
    
    }
    
    /**
     * @return \yii\db\ActiveQuery
     */
     
    public function getUser()
    {
    	return $this->hasOne(User::className(), ['id' => 'user_id']);
    }
    
    /**
     * @get Username
     */
     
    public function getUsername()
    {
    	return $this->user->username;
    }
    
    /**
     * @getUserId
     */
     
    public function getUserId()
    {
    	return $this->user ? $this->user->id : 'none';
    }
    
    /**
     * @getUserLink
     */
    
    public function getUserLink()
    {
    	$url = Url::to(['user/view', 'id'=>$this->UserId]);
    	$options = [];
    	return Html::a($this->getUserName(), $url, $options);
    }
    
    /**
     * @getProfileLink
     */
    
    public function getProfileIdLink()
    {
    	$url = Url::to(['profile/update', 'id'=>$this->id]);
    	$options = [];
    	return Html::a($this->id, $url, $options);
    }
}
