(function (ng) {
    var mod = ng.module("videosModule");
    mod.constant("videosContext", "api/999/videos");
    mod.controller('videosCtrl', ['$scope', '$http', 'videosContext', '$state',
        function ($scope, $http, videosContext, $state) {
            $http.get(videosContext).then(function (response) {
                $scope.videoRecords = response.data;
            });
            
            if ($state.params.videoId !== undefined && ($state.params.videoId !== null)) {
                $http.get(videosContext + '/' + $state.params.videoId).then(function (response) {
                      $scope.videoRecords = response.data.videos;
                  $scope.currentVideo = response.data;
                });
            }
        }
    ]);
}
)(angular);