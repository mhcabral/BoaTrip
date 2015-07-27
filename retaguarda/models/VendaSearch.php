<?php

namespace app\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\Venda;

/**
 * VendaSearch represents the model behind the search form about `app\models\Venda`.
 */
class VendaSearch extends Venda
{
    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id', 'profile_id', 'passagem_id', 'venda_status_id'], 'integer'],
            [['data', 'cartao_numero', 'validade'], 'safe'],
            [['valor'], 'number'],
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
        $query = Venda::find();

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
            'data' => $this->data,
            'valor' => $this->valor,
            'profile_id' => $this->profile_id,
            'passagem_id' => $this->passagem_id,
            'venda_status_id' => $this->venda_status_id,
        ]);

        $query->andFilterWhere(['like', 'cartao_numero', $this->cartao_numero])
            ->andFilterWhere(['like', 'validade', $this->validade]);

        return $dataProvider;
    }
}
