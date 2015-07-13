<?php

namespace app\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\Localidade;

/**
 * LocalidadeSearch represents the model behind the search form about `app\models\Localidade`.
 */
class LocalidadeSearch extends Localidade
{
    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id', 'uf_id'], 'integer'],
            [['localidade_nome', 'localidade_url'], 'safe'],
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
        $query = Localidade::find();

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
            'uf_id' => $this->uf_id,
        ]);

        $query->andFilterWhere(['like', 'localidade_nome', $this->localidade_nome])
            ->andFilterWhere(['like', 'localidade_url', $this->localidade_url]);

        return $dataProvider;
    }
}
