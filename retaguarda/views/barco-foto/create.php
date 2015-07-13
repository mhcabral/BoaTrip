<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\BarcoFoto */

$this->title = 'Create Barco Foto';
$this->params['breadcrumbs'][] = ['label' => 'Barco Fotos', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="barco-foto-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
