<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "Empresa".
 *
 * @property string $id
 * @property string $nome
 * @property string $razao_social
 * @property string $cnpj
 * @property string $contato
 * @property string $endereco
 * @property string $cep
 * @property string $telefone
 * @property string $user_id
 */
class Empresa extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'empresa';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['nome', 'razao_social', 'cnpj', 'user_id'], 'required'],
            [['user_id'], 'integer'],
            [['nome', 'razao_social', 'endereco'], 'string', 'max' => 50],
            [['cnpj'], 'string', 'max' => 14],
            [['contato', 'telefone'], 'string', 'max' => 20],
            [['cep'], 'string', 'max' => 8]
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
            'razao_social' => 'Razao Social',
            'cnpj' => 'Cnpj',
            'contato' => 'Contato',
            'endereco' => 'Endereco',
            'cep' => 'Cep',
            'telefone' => 'Telefone',
            'user_id' => 'User ID',
        ];
    }
}
