(function (ng) {
        var mod = ng.module("eVideoModule");
        mod.constant("eVideosContext", "api/eVideos");
        mod.controller('eVideoCtrl', ['$scope', '$http', 'eVideosContext',
            function ($scope, $http, eVideosContext) {
                $http.get('data/eVideos.json').then(function (response) {
                    $scope.eVideosRecords = response.data;
                });
            }
        ]);
    }
)(window.angular);