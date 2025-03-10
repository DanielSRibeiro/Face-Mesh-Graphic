package com.example.facemeshgraphic.model.usecase

import com.example.facemeshgraphic.data.FaceMeshAnalyzer
import com.example.facemeshgraphic.model.FaceMeshModel
import com.example.facemeshgraphic.model.ResultStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GetFaceAnalyzerUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<FaceMeshAnalyzer>>

    data class Params(
        val onFaceMeshDetected: (FaceMeshModel) -> Unit
    )
}

class GetFaceAnalyzerUseCaseImpl @Inject constructor() : GetFaceAnalyzerUseCase {
    override fun invoke(params: GetFaceAnalyzerUseCase.Params): Flow<ResultStatus<FaceMeshAnalyzer>> {
        return flow {
            emit(ResultStatus.Loading)
            val analyzer = FaceMeshAnalyzer(
                onFaceMeshDetected = params.onFaceMeshDetected
            )
            emit(ResultStatus.Success(analyzer))
        }.catch {
            emit(ResultStatus.Failure(throwable = it))
        }
    }


}