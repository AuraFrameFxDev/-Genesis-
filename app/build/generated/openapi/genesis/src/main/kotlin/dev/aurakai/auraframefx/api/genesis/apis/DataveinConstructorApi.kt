package dev.aurakai.auraframefx.api.genesis.apis

import dev.aurakai.auraframefx.api.genesis.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.squareup.moshi.Json

import dev.aurakai.auraframefx.api.genesis.models.RomAnalysisResponse

import okhttp3.MultipartBody

interface DataveinConstructorApi {

    /**
    * enum for parameter analysisType
    */
    enum class AnalysisTypeDataveinAnalyzeRomPost(val value: kotlin.String) {
        @Json(name = "security") security("security"),
        @Json(name = "compatibility") compatibility("compatibility"),
        @Json(name = "modification") modification("modification")
    }

    /**
     * POST datavein/analyze-rom
     * Analyze ROM/boot.img file
     * Performs AI-powered analysis of Android ROM or boot.img files
     * Responses:
     *  - 200: ROM analysis completed
     *
     * @param romFile ROM or boot.img file to analyze (optional)
     * @param analysisType Type of analysis to perform (optional)
     * @return [Call]<[RomAnalysisResponse]>
     */
    @Multipart
    @POST("datavein/analyze-rom")
    fun dataveinAnalyzeRomPost(@Part romFile: MultipartBody.Part? = null, @Part("analysisType") analysisType: kotlin.String? = null): Call<RomAnalysisResponse>

}
