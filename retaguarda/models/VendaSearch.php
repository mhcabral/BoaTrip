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
	public $dataIni;
	public $dataFim;
	
    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id', 'profile_id', 'passagem_id', 'venda_status_id'], 'integer'],
            [['data', 'dataIni', 'dataFim', 'cartao_numero', 'validade'], 'safe'],
            [['valor'], 'number'],
        ];
    }
    
    public function attributeLabels()
    {
    	return [
    			'id' => 'ID',
    			'data' => 'Data',
    			'dataIni' => 'Data Inicial',
    			'dataFim' => 'Data Final',
    			'valor' => 'Valor',
    			'cartao_numero' => 'Cartao Numero',
    			'validade' => 'Validade',
    			'profile_id' => 'Profile',
    			'passagem_id' => 'Passagem',
    			'venda_status_id' => 'Status',
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
            ->andFilterWhere(['like', 'validade', $this->validade])
        	->andFilterWhere(['>=', 'DATE(data)', $this->dataIni])
        	->andFilterWhere(['<=', 'DATE(data)', $this->dataFim]);

        return $dataProvider;
    }
}
