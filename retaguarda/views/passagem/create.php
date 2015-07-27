<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Passagem */

$this->title = 'Criar Passagem';
$this->params['breadcrumbs'][] = ['label' => 'Passagems', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="passagem-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
