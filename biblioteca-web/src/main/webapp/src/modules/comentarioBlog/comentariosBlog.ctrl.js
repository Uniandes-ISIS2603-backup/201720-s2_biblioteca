(function (ng) {
    var mod = ng.module("comentariosBlogModule");
    mod.constant("comentariosBlogContext", "api/999/comentarios/blog");
       mod.constant("comentariosContext", "api/999/comentarios");
    mod.controller('comentariosBlogCtrl', ['$scope', '$http', 'comentariosBlogContext', '$state', 
        function ($scope, $http, comentariosBlogContext, $state) {
            $http.get(comentariosBlogContext+ "/"+ $state.params.blogId ).then(function (response) {
                $scope.comentarioBlogRecords = response.data;
            });
            
          
            
        }
    ]);
}
)(angular);