<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Localidade */

$this->title = 'Create Localidade';
$this->params['breadcrumbs'][] = ['label' => 'Localidades', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="localidade-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
