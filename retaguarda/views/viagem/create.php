<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Viagem */

$this->title = 'Create Viagem';
$this->params['breadcrumbs'][] = ['label' => 'Viagems', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="viagem-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
