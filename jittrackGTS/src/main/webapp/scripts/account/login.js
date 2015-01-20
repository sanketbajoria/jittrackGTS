'use strict';

angular.module('jittrackApp')
  .controller('LoginCtrl', function ($scope, $translate, Auth, Data, $location) {
    $scope.user = {};
    $scope.errors = {};
    $scope.change = function(){
    	$scope.submitted = false;
    	$scope.errors.other = false;
    }
    Data.getFieldData('language').then(function(data){
    	console.log(data["languages"]);
    	$scope.languages = angular.fromJson(data["languages"]);
    	angular.forEach($scope.languages,function(lang){
    		if(lang.code == $translate.use()){
    			$scope.user.language = lang.name
    		}
    	});
    	
    });
    $scope.changeLang = function($item){
    	 $translate.use($item.code);
    }
    $scope.login = function(form) {
      $scope.submitted = true;
      
      if(form.$valid) {
        Auth.login({
          email: $scope.user.id,
          password: $scope.user.password
        })
        .then( function() {
          // Logged in, redirect to home
          $location.path('/');
        })
        .catch( function(err) {
          err = err.data;
          $scope.errors.other = err.message;
        });
      }
    };
  });