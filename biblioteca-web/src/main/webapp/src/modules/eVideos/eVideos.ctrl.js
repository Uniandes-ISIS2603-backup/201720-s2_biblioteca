(function (ng) {
        var mod = ng.module("eVideoModule");
        mod.constant("eVideosContext", "api/999/eVideos");
        mod.controller('eVideoCtrl', ['$scope', '$http', 'eVideosContext', '$state',
            function ($scope, $http, eVideosContext, $state) {
                $http.get(eVideosContext).then(function (response) {
                    $scope.eVideosRecords = response.data;
                });
                if (($state.params.eVideoId !== undefined) && ($state.params.eVideoId !== null)) {
                    $http.get(eVideosContext + '/' + $state.params.eVideoId).then(function (response) {
                        $scope.currentEVideo = response.data;
                    });

                }
            }
        ]);
    })(window.angular);

/**
 *
 * EN CASO DE QUE NO SIRVAN LOS REST, PROBAR CON LOS JSON, CODIGO COMENTADO ABAJO   |
 *                                                                                  V
 *
 (function (ng) {
        var mod = ng.module("eVideoModule");
        mod.constant("eVideosContext", "api/999/eVideos");
        mod.controller('eVideoCtrl', ['$scope', '$http', 'eVideosContext', '$state',
            function ($scope, $http, eVideosContext, $state) {
                $http.get('data/eVideos.json').then(function (response) {
                    $scope.eVideosRecords = response.data;
                });
                if (($state.params.eVideoId !== undefined) && ($state.params.eVideoId !== null) && ($scope.eVideosRecords)) {
                    // $http.get(eVideosContext + '/' + $state.params.eVideoId).then(function (response) {
                    //     $scope.currentEVideo = response.data;
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
 */

