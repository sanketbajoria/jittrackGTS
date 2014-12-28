'use strict';

jittrackgtsApp.controller('AccountController', function ($scope, resolvedAccount, Accounts) {

        $scope.accounts = resolvedAccount;

        $scope.create = function () {
            Accounts.save($scope.account,
                function () {
                    $scope.accounts = Accounts.query();
                    $('#saveAccountModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.account = Accounts.get({id: id});
            $('#saveAccountModal').modal('show');
        };

        $scope.delete = function (id) {
            Accounts.delete({id: id},
                function () {
                    $scope.accounts = Accounts.query();
                });
        };

        $scope.clear = function () {
            $scope.account = {accountType: null, allowNotify: null, allowWebService: null, autoAddDevices: null, contactEmail: null, contactName: null, contactPhone: null, expirationTime: null, emailProperties: null, economyUnits: null, distanceUnits: null, displayName: null, description: null, defaultUser: null, creationTime: null, currencyUnits: null, dcsPropertiesID: null, fuelCostPerLiter: null, geocoderMode: null, isActive: null, isBorderCrossing: null, lastLoginTime: null, lastUpdateTime: null, latLonFormat: null, maximumDevices: null, maxPingCount: null, notes: null, notifyEmail: null, passwdChangeTime: null, passwdQueryTime: null, password: null, pressureUnits: null, privateLabelName: null, retainedEventAge: null, smsEnabled: null, smsProperties: null, speedUnits: null, temperatureUnits: null, timeZone: null, totalPingCount: null, volumeUnits: null, id: null};
        };
    });
