<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel app\models\BarcoSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Barcos';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="barco-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Create Barco', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            'nome',
            'descricao',
            'tripulantes',
            'empresa_id',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

</div>
