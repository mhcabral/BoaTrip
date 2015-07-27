<?php

namespace app\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\Viagem;

/**
 * ViagemSearch represents the model behind the search form about `app\models\Viagem`.
 */
class ViagemSearch extends Viagem
{
    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id', 'barco_id', 'localidade_origem', 'localidade_destino'], 'integer'],
            [['data_saida', 'data_chegada', 'percurso'], 'safe'],
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
        $query = Viagem::find();

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
            'data_saida' => $this->data_saida,
            'data_chegada' => $this->data_chegada,
            'barco_id' => $this->barco_id,
            'localidade_origem' => $this->localidade_origem,
            'localidade_destino' => $this->localidade_destino,
        ]);

        $query->andFilterWhere(['like', 'percurso', $this->percurso]);

        return $dataProvider;
    }
}
