<?php

namespace app\models;

use Yii;
use yii\db\ActiveRecord;
use yii\db\Expression;

/**
 * This is the model class for table "User".
 *
 * @property string $id
 * @property string $username
 * @property string $email
 * @property string $pass
 * @property string $type
 * @property string $data_entrada
 */
class User extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'User';
    }
    
    public function behaviors()
    {
    	return [
    		'timestamp' => [
    			'class' => 'yii\behaviors\TimestampBehavior',
    			'attributes' => [
    					ActiveRecord::EVENT_BEFORE_INSERT => ['data_entrada'],
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
            [['username', 'email', 'pass', 'type'], 'required'],
            [['type'], 'string'],
            [['username', 'email'], 'string', 'max' => 255],
            [['pass'], 'string', 'max' => 64],
            [['username'], 'unique'],
            [['email'], 'unique']
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'username' => 'Username',
            'email' => 'Email',
            'pass' => 'Pass',
            'type' => 'Type',
        ];
    }
}
