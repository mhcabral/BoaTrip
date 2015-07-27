<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel app\models\UfSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Ufs';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="uf-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Criar Uf', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            'uf_nome',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

</div>
