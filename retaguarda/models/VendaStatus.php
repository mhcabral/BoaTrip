<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "venda_status".
 *
 * @property integer $id
 * @property string $venda_nome
 * @property integer $venda_valor
 *
 * @property Venda[] $vendas
 */
class VendaStatus extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'venda_status';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['venda_nome', 'venda_valor'], 'required'],
            [['venda_valor'], 'integer'],
            [['venda_nome'], 'string', 'max' => 45]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'venda_nome' => 'Venda Nome',
            'venda_valor' => 'Venda Valor',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getVendas()
    {
        return $this->hasMany(Venda::className(), ['venda_status_id' => 'id']);
    }
}
