<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "genero".
 *
 * @property integer $id
 * @property string $genero_nome
 *
 * @property Passageiro[] $passageiros
 */
class Genero extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'genero';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['genero_nome'], 'required'],
            [['genero_nome'], 'string', 'max' => 45]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'genero_nome' => 'Gênero',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getPassageiros()
    {
        return $this->hasMany(Passageiro::className(), ['genero_id' => 'id']);
    }
}
