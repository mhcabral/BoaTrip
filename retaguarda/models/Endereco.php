<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "endereco".
 *
 * @property string $id
 * @property string $endereco
 * @property string $cep
 * @property string $telefone
 *
 * @property Empresa[] $empresas
 * @property Passageiro[] $passageiros
 */
class Endereco extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'endereco';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['endereco', 'cep', 'telefone'], 'required'],
            [['endereco'], 'string', 'max' => 50],
            [['cep'], 'string', 'max' => 8],
            [['telefone'], 'string', 'max' => 20]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'endereco' => 'Endereco',
            'cep' => 'Cep',
            'telefone' => 'Telefone',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getEmpresas()
    {
        return $this->hasMany(Empresa::className(), ['endereco_id' => 'id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getPassageiros()
    {
        return $this->hasMany(Passageiro::className(), ['endereco_id' => 'id']);
    }
}
