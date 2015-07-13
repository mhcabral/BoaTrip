<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "viagem".
 *
 * @property string $id
 * @property string $data_saida
 * @property string $data_chegada
 * @property string $valor
 * @property string $valor_desconto
 * @property string $data_desconto_ini
 * @property string $data_desconto_fim
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
            [['data_saida', 'data_chegada', 'valor', 'barco_id', 'localidade_origem', 'localidade_destino'], 'required'],
            [['data_saida', 'data_chegada', 'data_desconto_ini'], 'safe'],
            [['valor', 'valor_desconto'], 'number'],
            [['barco_id', 'localidade_origem', 'localidade_destino'], 'integer'],
            [['data_desconto_fim', 'percurso'], 'string', 'max' => 45]
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
            'valor' => 'Valor',
            'valor_desconto' => 'Desconto',
            'data_desconto_ini' => 'Início Desconto',
            'data_desconto_fim' => 'Fom Desconto',
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
