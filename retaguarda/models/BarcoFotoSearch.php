<?php

namespace app\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\BarcoFoto;

/**
 * BarcoFotoSearch represents the model behind the search form about `app\models\BarcoFoto`.
 */
class BarcoFotoSearch extends BarcoFoto
{
    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id', 'barco_id'], 'integer'],
            [['foto_url'], 'safe'],
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
        $query = BarcoFoto::find();

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
            'barco_id' => $this->barco_id,
        ]);

        $query->andFilterWhere(['like', 'foto_url', $this->foto_url]);

        return $dataProvider;
    }
}
