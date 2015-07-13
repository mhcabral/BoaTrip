<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\Uf */

$this->title = 'Update Uf: ' . ' ' . $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Ufs', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id, 'url' => ['view', 'id' => $model->id, 'uf_nome' => $model->uf_nome]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="uf-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>