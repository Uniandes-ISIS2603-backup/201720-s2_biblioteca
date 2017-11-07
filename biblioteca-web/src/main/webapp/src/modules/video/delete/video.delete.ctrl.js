(function (ng) {
    var mod = ng.module("videosModule");
    mod.controller('videosDeleteCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            var idVideo= $state.params.videoId;
            $scope.deleteVideo = function () {
                 $http.delete('api/999/videos' + '/' + idVideo, {}).then(function (response) {
                    $state.go('videosList', {videoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);