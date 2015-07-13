<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "passageiro".
 *
 * @property string $id
 * @property string $nome
 * @property string $cpf
 * @property integer $genero_id
 * @property string $endereco_id
 * @property string $user_id
 *
 * @property Endereco $endereco
 * @property User $user
 * @property Genero $genero
 * @property Venda[] $vendas
 */
class Passageiro extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'passageiro';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['nome', 'cpf', 'genero_id', 'endereco_id', 'user_id'], 'required'],
            [['genero_id', 'endereco_id', 'user_id'], 'integer'],
            [['nome'], 'string', 'max' => 50],
            [['cpf'], 'string', 'max' => 11]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'nome' => 'Nome',
            'cpf' => 'Cpf',
            'genero_id' => 'Genero ID',
            'endereco_id' => 'Endereco ID',
            'user_id' => 'User ID',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getEndereco()
    {
        return $this->hasOne(Endereco::className(), ['id' => 'endereco_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getUser()
    {
        return $this->hasOne(User::className(), ['id' => 'user_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getGenero()
    {
        return $this->hasOne(Genero::className(), ['id' => 'genero_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getVendas()
    {
        return $this->hasMany(Venda::className(), ['passageiro_id' => 'id']);
    }
}
