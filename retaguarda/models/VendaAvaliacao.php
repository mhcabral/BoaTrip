<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "venda_avaliacao".
 *
 * @property string $id
 * @property integer $estrela1
 * @property integer $estrela2
 * @property integer $estrela3
 * @property string $mensagem
 * @property string $venda_id
 *
 * @property Venda $venda
 */
class VendaAvaliacao extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'venda_avaliacao';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['estrela1', 'estrela2', 'estrela3', 'venda_id'], 'integer'],
            [['venda_id'], 'required'],
            [['mensagem'], 'string', 'max' => 255]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'estrela1' => 'Estrela1',
            'estrela2' => 'Estrela2',
            'estrela3' => 'Estrela3',
            'mensagem' => 'Mensagem',
            'venda_id' => 'Venda ID',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getVenda()
    {
        return $this->hasOne(Venda::className(), ['id' => 'venda_id']);
    }
}
