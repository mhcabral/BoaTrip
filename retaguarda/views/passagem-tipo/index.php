<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel app\models\PassagemTipoSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Passagem Tipos';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="passagem-tipo-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Criar Passagem Tipo', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            'passagem_tipo_nome',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

</div>
