<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model app\models\Viagem */

$this->title = $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Viagems', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="viagem-view">

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
            'data_saida',
            'data_chegada',
            //'valor',
            //'valor_desconto',
            //'data_desconto_ini',
            //'data_desconto_fim',
            'percurso',
            'barco_id',
            'localidade_origem',
            'localidade_destino',
        ],
    ]) ?>

</div>
