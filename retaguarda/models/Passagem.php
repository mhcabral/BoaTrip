<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "passagem".
 *
 * @property string $id
 * @property string $viagem_id
 * @property integer $quantidade
 * @property integer $passagem_tipo_id
 * @property string $valor
 * @property string $valor_desconto
 * @property string $data_desconto_ini
 * @property string $data_desconto_fim
 *
 * @property PassagemTipo $passagemTipo
 * @property Viagem $viagem
 * @property Venda[] $vendas
 */
class Passagem extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'passagem';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['viagem_id', 'quantidade', 'passagem_tipo_id', 'valor'], 'required'],
            [['viagem_id', 'quantidade', 'passagem_tipo_id'], 'integer'],
            [['valor', 'valor_desconto'], 'number'],
            [['data_desconto_ini', 'data_desconto_fim'], 'safe']
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'viagem_id' => 'Viagem ID',
            'quantidade' => 'Quantidade',
            'passagem_tipo_id' => 'Passagem Tipo ID',
            'valor' => 'Valor',
            'valor_desconto' => 'Valor Desconto',
            'data_desconto_ini' => 'Data Desconto Ini',
            'data_desconto_fim' => 'Data Desconto Fim',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getPassagemTipo()
    {
        return $this->hasOne(PassagemTipo::className(), ['id' => 'passagem_tipo_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getViagem()
    {
        return $this->hasOne(Viagem::className(), ['id' => 'viagem_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getVendas()
    {
        return $this->hasMany(Venda::className(), ['passagem_id' => 'id']);
    }
}
