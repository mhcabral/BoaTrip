<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "barco_foto".
 *
 * @property string $id
 * @property string $foto_url
 * @property string $barco_id
 *
 * @property Barco $barco
 */
class BarcoFoto extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'barco_foto';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['foto_url', 'barco_id'], 'required'],
            [['barco_id'], 'integer'],
            [['foto_url'], 'string', 'max' => 255]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'foto_url' => 'Foto Url',
            'barco_id' => 'Barco ID',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getBarco()
    {
        return $this->hasOne(Barco::className(), ['id' => 'barco_id']);
    }
}
