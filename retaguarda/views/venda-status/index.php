<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel app\models\VendaStatusSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Venda Statuses';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="venda-status-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Criar Venda Status', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            'venda_nome',
            'venda_valor',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

</div>
