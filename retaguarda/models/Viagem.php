<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "viagem".
 *
 * @property string $id
 * @property string $data_saida
 * @property string $data_chegada
 * @property string $percurso
 * @property string $barco_id
 * @property string $localidade_origem
 * @property string $localidade_destino
 *
 * @property Passagem[] $passagems
 * @property Barco $barco
 * @property Localidade $localidadeDestino
 * @property Localidade $localidadeOrigem
 */
class Viagem extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'viagem';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['data_saida', 'data_chegada', 'barco_id', 'localidade_origem', 'localidade_destino'], 'required'],
            [['data_saida', 'data_chegada'], 'safe'],
            [['barco_id', 'localidade_origem', 'localidade_destino'], 'integer'],
            [['percurso'], 'string', 'max' => 255]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'data_saida' => 'Saida',
            'data_chegada' => 'Chegada',
            'percurso' => 'Percurso',
            'barco_id' => 'Barco',
            'localidade_origem' => 'Origem',
            'localidade_destino' => 'Destino',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getPassagems()
    {
        return $this->hasMany(Passagem::className(), ['viagem_id' => 'id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getBarco()
    {
        return $this->hasOne(Barco::className(), ['id' => 'barco_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getLocalidadeDestino()
    {
        return $this->hasOne(Localidade::className(), ['id' => 'localidade_destino']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getLocalidadeOrigem()
    {
        return $this->hasOne(Localidade::className(), ['id' => 'localidade_origem']);
    }
}
