'use strict';

jittrackgtsApp.factory('Accounts', function ($resource) {
        return $resource('app/rest/accounts/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
