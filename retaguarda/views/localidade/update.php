<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\Localidade */

$this->title = 'Atualizar Localidade: ' . ' ' . $model->id;
$this->params['breadcrumbs'][] = ['label' => 'Localidades', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id, 'url' => ['view', 'id' => $model->id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="localidade-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
