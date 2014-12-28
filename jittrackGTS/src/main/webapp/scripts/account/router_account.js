'use strict';

jittrackgtsApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/account', {
                    templateUrl: 'views/accounts.html',
                    controller: 'AccountController',
                    resolve:{
                        resolvedAccount: ['Accounts', function (Accounts) {
                            return Accounts.query().$promise;
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
