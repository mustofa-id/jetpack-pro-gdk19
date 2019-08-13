package id.mustofa.app.academy.data.source.remote

import android.app.Application
import id.mustofa.app.academy.data.source.remote.response.ContentResponse
import id.mustofa.app.academy.data.source.remote.response.CourseResponse
import id.mustofa.app.academy.data.source.remote.response.ModuleResponse
import org.json.JSONObject

/**
 * @author Habib Mustofa
 * Indonesia on 13/08/19
 */
class JsonHelper(private val app: Application) {

    private fun parseFile(fileName: String): String {
        return app.assets
            .open(fileName)
            .bufferedReader()
            .use { it.readText() }
    }

    private fun listJSON(fileName: String, parent: String): List<JSONObject>? {
        return JSONObject(parseFile(fileName)).getJSONArray(parent)
            ?.let { 0.until(it.length()).map { i -> it.getJSONObject(i) } }
    }

    fun loadCourses(): List<CourseResponse> {
        return listJSON("CourseResponses.json", "courses")?.map {
            CourseResponse(
                it.getString("id"),
                it.getString("title"),
                it.getString("description"),
                it.getString("date"),
                it.getString("imagePath")
            )
        }!!
    }

    fun loadModules(courseId: String): List<ModuleResponse> {
        return listJSON("Module_$courseId.json", "modules")?.map {
            ModuleResponse(
                it.getString("moduleId"),
                courseId,
                it.getString("title"),
                it.getString("position").toInt()
            )
        }!!
    }

    fun loadContent(moduleId: String): ContentResponse {
        return ContentResponse(
            moduleId,
            JSONObject(
                parseFile("Content_$moduleId.json")
            ).getString("content")
        )
    }
}