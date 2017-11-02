(function (ng) {
        var mod = ng.module("eVideoModule");
        mod.constant("eVideosContext", "api/eVideos");
        mod.controller('eVideoCtrl', ['$scope', '$http', 'eVideosContext', '$state',
            function ($scope, $http, eVideosContext, $state) {
                $http.get('data/eVideos.json').then(function (response) {
                    $scope.eVideosRecords = response.data;
                });
                if (($state.params.eVideoId !== undefined) && ($state.params.eVideoId !== null) && ($scope.eVideosRecords)) {
                    // $http.get(eBooksContext + '/' + $state.params.eBookId).then(function (response) {
                    //     $scope.currentEBook = response.data;
                    // });
                    $scope.eVideosRecords.forEach(function(elem){
                        if (elem.id === $state.params.eVideoId)
                            $scope.currentEVideo = elem;
                    });
                }
            }
        ]);
    }
)(window.angular);