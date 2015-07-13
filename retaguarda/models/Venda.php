<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "venda".
 *
 * @property string $id
 * @property string $data
 * @property string $valor
 * @property string $cartao_numero
 * @property string $validade
 * @property string $passageiro_id
 * @property string $passagem_id
 * @property integer $venda_status_id
 *
 * @property Passageiro $passageiro
 * @property Passagem $passagem
 * @property VendaStatus $vendaStatus
 * @property VendaAvaliacao[] $vendaAvaliacaos
 */
class Venda extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'venda';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['data', 'valor', 'cartao_numero', 'validade', 'passageiro_id', 'passagem_id', 'venda_status_id'], 'required'],
            [['data'], 'safe'],
            [['valor'], 'number'],
            [['passageiro_id', 'passagem_id', 'venda_status_id'], 'integer'],
            [['cartao_numero'], 'string', 'max' => 20],
            [['validade'], 'string', 'max' => 5]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'data' => 'Data',
            'valor' => 'Valor',
            'cartao_numero' => 'Cartao Numero',
            'validade' => 'Validade',
            'passageiro_id' => 'Passageiro ID',
            'passagem_id' => 'Passagem ID',
            'venda_status_id' => 'Venda Status ID',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getPassageiro()
    {
        return $this->hasOne(Passageiro::className(), ['id' => 'passageiro_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getPassagem()
    {
        return $this->hasOne(Passagem::className(), ['id' => 'passagem_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getVendaStatus()
    {
        return $this->hasOne(VendaStatus::className(), ['id' => 'venda_status_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getVendaAvaliacaos()
    {
        return $this->hasMany(VendaAvaliacao::className(), ['venda_id' => 'id']);
    }
}
