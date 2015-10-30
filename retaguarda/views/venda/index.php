<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel app\models\VendaSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Vendas';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="venda-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?php //<?= Html::a('Criar Venda', ['create'], ['class' => 'btn btn-success']);
		
		?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        //'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            'data',
            'valor',
            'cartao_numero',
            'validade',
            // 'profile_id',
            // 'passagem_id',
            // 'venda_status_id',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

</div>
