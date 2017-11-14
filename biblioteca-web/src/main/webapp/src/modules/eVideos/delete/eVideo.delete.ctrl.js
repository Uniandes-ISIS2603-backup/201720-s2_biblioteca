(function (ng) {
        var mod = ng.module("eVideoModule");
        mod.constant("eVideosContext", "api/999/eVideos");
        mod.controller('eVideoDeleteCtrl', ['$scope', '$http', 'eVideosContext', '$state',
            function ($scope, $http, eVideosContext, $state) {
                var idEVideo = $state.params.eVideoId;
                $scope.deleteEVideo = function () {
                    $http.delete(eVideosContext + '/' + idEVideo, {}).then(function (response) {
                        $state.go('eVideosList', {eVideoId: response.data.id}, {reload: true});
                    });
                };
            }
        ]);
    })(angular);