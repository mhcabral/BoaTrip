<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model app\models\Passagem */

$this->title = $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Passagems', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="passagem-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Update', ['update', 'id' => $model->id], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id' => $model->id], [
            'class' => 'btn btn-danger',
            'data' => [
                'confirm' => 'Are you sure you want to delete this item?',
                'method' => 'post',
            ],
        ]) ?>
    </p>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            'id',
            'viagem_id',
            'quantidade',
            'passagem_tipo_id',
            'valor',
            'valor_desconto',
            'data_desconto_ini',
            'data_desconto_fim',
        ],
    ]) ?>

</div>
