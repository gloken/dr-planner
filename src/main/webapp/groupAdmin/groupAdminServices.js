'use strict';

var groupAdminServices = angular.module('groupAdminServices', ['ngResource']);

groupAdminServices.factory('CompetitionGroups', ['$resource',
    function ($resource) {
        return $resource('/rest/competitionGroups', {}, {
            query: {method: 'GET', params:{}, isArray: true }
        });
    }
]),

groupAdminServices.factory('CompetitionGroups2', ['$resource',
    function ($resource) {
        return $resource('/rest/competitionGroups', {}, {
            query: {method: 'GET', params:{}, isArray: true }
        });
    }
]);