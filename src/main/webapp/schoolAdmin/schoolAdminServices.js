'use strict';

var schoolAdminServices = angular.module('schoolAdminServices', ['ngResource']);

schoolAdminServices.factory('DancersWithUnknownSchools', ['$resource',
    function ($resource) {
        return $resource('/rest/schoolAdmin', {}, {
            query: {method: 'GET', params:{}, isArray: true}
        });
    }
]);