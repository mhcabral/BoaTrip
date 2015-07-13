<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "uf".
 *
 * @property integer $id
 * @property string $uf_nome
 *
 * @property Localidade[] $localidades
 */
class Uf extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'uf';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['uf_nome'], 'required'],
            [['uf_nome'], 'string', 'max' => 50]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'uf_nome' => 'Unidade Federativa',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getLocalidades()
    {
        return $this->hasMany(Localidade::className(), ['uf_id' => 'id']);
    }
}
