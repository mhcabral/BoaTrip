<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Barco */

$this->title = 'Create Barco';
$this->params['breadcrumbs'][] = ['label' => 'Barcos', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="barco-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
