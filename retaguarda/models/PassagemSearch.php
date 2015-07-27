<?php

namespace app\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\Passagem;

/**
 * PassagemSearch represents the model behind the search form about `app\models\Passagem`.
 */
class PassagemSearch extends Passagem
{
    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id', 'viagem_id', 'quantidade', 'passagem_tipo_id'], 'integer'],
            [['valor', 'valor_desconto'], 'number'],
            [['data_desconto_ini', 'data_desconto_fim'], 'safe'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function scenarios()
    {
        // bypass scenarios() implementation in the parent class
        return Model::scenarios();
    }

    /**
     * Creates data provider instance with search query applied
     *
     * @param array $params
     *
     * @return ActiveDataProvider
     */
    public function search($params)
    {
        $query = Passagem::find();

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
        ]);

        $this->load($params);

        if (!$this->validate()) {
            // uncomment the following line if you do not want to return any records when validation fails
            // $query->where('0=1');
            return $dataProvider;
        }

        $query->andFilterWhere([
            'id' => $this->id,
            'viagem_id' => $this->viagem_id,
            'quantidade' => $this->quantidade,
            'passagem_tipo_id' => $this->passagem_tipo_id,
            'valor' => $this->valor,
            'valor_desconto' => $this->valor_desconto,
            'data_desconto_ini' => $this->data_desconto_ini,
            'data_desconto_fim' => $this->data_desconto_fim,
        ]);

        return $dataProvider;
    }
}
