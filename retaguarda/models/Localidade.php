<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "localidade".
 *
 * @property string $id
 * @property string $localidade_nome
 * @property integer $uf_id
 * @property string $localidade_url
 *
 * @property Uf $uf
 * @property Viagem[] $viagems
 * @property Viagem[] $viagems0
 */
class Localidade extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'localidade';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['localidade_nome', 'uf_id'], 'required'],
            [['uf_id'], 'integer'],
            [['localidade_nome', 'localidade_url'], 'string', 'max' => 255]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'localidade_nome' => 'Nome da Localidade',
            'uf_id' => 'Unidade Federativa',
            'localidade_url' => 'Mapa',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getUf()
    {
        return $this->hasOne(Uf::className(), ['id' => 'uf_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getViagems()
    {
        return $this->hasMany(Viagem::className(), ['localidade_destino' => 'id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getViagems0()
    {
        return $this->hasMany(Viagem::className(), ['localidade_origem' => 'id']);
    }
}
