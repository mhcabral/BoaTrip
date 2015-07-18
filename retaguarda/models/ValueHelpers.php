<?php
namespace app\models;
 
use yii;
use app\models\Role;
use app\models\Status;
use app\models\User;
  
class ValueHelpers  
{    
        
    public static function roleMatch($role_name)
    {
        
         $userHasRoleName = Yii::$app->user->identity->role->role_name;
         
              return $userHasRoleName == $role_name ? true : false;
        
          
    }
         
    public static function getUsersRoleValue($userId=null)
    {
    	if ($userId == null){
        	$usersRoleValue = Yii::$app->user->identity->role->role_value;    
        	return isset($usersRoleValue) ? $usersRoleValue : false;   
        } else {
            
            
             $user = User::findOne($userId);
                                         
             $usersRoleValue = $user->role->role_value;
                          
                 return isset($usersRoleValue) ? $usersRoleValue : false;            
                       
        }
      
    }
         
    public static function getRoleValue($role_name)
    {
        
        $role = Role::find('role_value')
                      ->where(['role_name' => $role_name])
                      ->one();
                          
          return isset($role->role_value) ? $role->role_value : false;
                
    }
    
    public static function isRoleNameValid($role_name)
    {
        
        $role = Role::find('role_name')
                      ->where(['role_name' => $role_name])
                      ->one();
                          
          return isset($role->role_name) ? true : false;
                
    }
     
    public static function statusMatch($status_name)
    {
          
          $userHasStatusName = Yii::$app->user->identity->status->status_name;
         
                return $userHasStatusName == $status_name ? true : false;
                                               
    }
           
    public static function getStatusId($status_name)
    {
                      
        $status = Status::find('id')
                          ->where(['status_name' => $status_name])
                          ->one();
                          
            return isset($status->id) ? $status->id : false;
                    
    }
       
}