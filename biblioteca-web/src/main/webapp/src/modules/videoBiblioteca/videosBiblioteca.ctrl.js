(function (ng) {
    var mod = ng.module("videosBibliotecaModule");
    mod.constant("videosBibliotecaContext", "api/999/videos/biblioteca");
       mod.constant("videosContext", "api/999/videos");
    mod.controller('videosBibliotecaCtrl', ['$scope', '$http', 'videosBibliotecaContext', '$state', 
        function ($scope, $http, videosBibliotecaContext, $state) {
            $http.get(videosBibliotecaContext+ "/"+ $state.params.bibliotecaId ).then(function (response) {
                $scope.videoBibliotecaRecords = response.data;
            });
            
          
            
        }
    ]);
}
)(angular);