<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "passagem_tipo".
 *
 * @property integer $id
 * @property string $passagem_tipo_nome
 *
 * @property Passagem[] $passagems
 */
class PassagemTipo extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'passagem_tipo';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['passagem_tipo_nome'], 'required'],
            [['passagem_tipo_nome'], 'string', 'max' => 50]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'passagem_tipo_nome' => 'Passagem Tipo Nome',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getPassagems()
    {
        return $this->hasMany(Passagem::className(), ['passagem_tipo_id' => 'id']);
    }
}
