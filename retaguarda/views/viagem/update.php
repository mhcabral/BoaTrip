<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $model app\models\Viagem */

$this->title = 'Atualizar Viagem: ' . ' ' . $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Viagems', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id, 'url' => ['view', 'id' => $model->id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="viagem-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>
    
    <p>
        <?= Html::a('Criar Passagem', ['/passagem/create','id'=>$model->id], ['class' => 'btn btn-success']) ?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],
            'id',
            'quantidade',
            'passagem_tipo_id',
            'valor',
            ['class' => 'yii\grid\ActionColumn', 'controller' => 'passagem'],
        ],
    ]); ?>

</div>
