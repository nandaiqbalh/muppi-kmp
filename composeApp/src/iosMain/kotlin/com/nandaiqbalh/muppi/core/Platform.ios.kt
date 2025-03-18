package id.rawlabs.rnd.spacesofttoken

import com.mmk.kmpnotifier.notification.NotifierManager
import com.mmk.kmpnotifier.notification.configuration.NotificationPlatformConfiguration
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.Settings
import id.rawlabs.rnd.spacesofttoken.data.base.model.DeviceInfo
import id.rawlabs.rnd.spacesofttoken.utils.logging
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.refTo
import kotlinx.cinterop.toKString
import kotlinx.cinterop.value
import platform.CoreCrypto.CCHmac
import platform.CoreCrypto.CCHmacAlgorithm
import platform.CoreCrypto.CC_SHA256_DIGEST_LENGTH
import platform.CoreCrypto.kCCHmacAlgSHA256
import platform.Foundation.NSDate
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.cStringUsingEncoding
import platform.Foundation.lengthOfBytesUsingEncoding
import platform.Foundation.stringWithFormat
import platform.Foundation.timeIntervalSince1970
import platform.UIKit.UIDevice
import platform.darwin.sysctlbyname
import platform.posix.size_tVar

class IOSPlatform: Platform {
	override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
	override fun getHttpClientEngine(forMultipartData: Boolean): HttpClient {
		return HttpClient(Darwin) {
			engine {
				configureRequest {
					setAllowsCellularAccess(true)
				}
			}
		}
	}

	override fun getDevicePlatform(): String {
		return UIDevice.currentDevice.systemName() + "-" + UIDevice.currentDevice.systemVersion + "-" + getDeviceModel()
	}

	override fun isDebugMode(): Boolean {
		return true
	}

	override fun getDeviceInfo(): DeviceInfo {
		val deviceModel = getDeviceModel()
		val osName = UIDevice.currentDevice.systemName()
		val osVersion = UIDevice.currentDevice.systemVersion
		val deviceId = UIDevice.currentDevice.identifierForVendor?.UUIDString ?: "Unknown Device ID"

		return DeviceInfo(
			deviceId = deviceId,
			deviceProfile = deviceModel,
			deviceOS = "$osName $osVersion"
		)
	}
}

actual fun getPlatform(): Platform = IOSPlatform()


@OptIn(ExperimentalUnsignedTypes::class)
fun stringFromResult(result: UByteArray, length: Int): String {
	val hash = StringBuilder()
	for (index in 0 until length) {
		hash.append(NSString.stringWithFormat("%02x", result[index]))
	}
	return hash.toString()
}

private fun getDeviceModel(): String {
	val deviceIdentifier = getDeviceIdentifier() ?: return "Unknown iPhone"
	return deviceModelMap[deviceIdentifier] ?: deviceIdentifier
}

@OptIn(ExperimentalForeignApi::class)
private fun getDeviceIdentifier(): String? {
	return try {
		memScoped {
			val name = "hw.machine"
			val size = alloc<size_tVar>()
			sysctlbyname(name, null, size.ptr, null, 0u)
			val identifier = allocArray<ByteVar>(size.value.toInt())
			sysctlbyname(name, identifier, size.ptr, null, 0u)
			identifier.toKString()
		}
	} catch (e: Exception) {
		null
	}
}

val deviceModelMap = mapOf(
	"iPhone8,1" to "iPhone 6s",
	"iPhone8,2" to "iPhone 6s Plus",
	"iPhone9,1" to "iPhone 7", "iPhone9,3" to "iPhone 7",
	"iPhone9,2" to "iPhone 7 Plus", "iPhone9,4" to "iPhone 7 Plus",
	"iPhone10,1" to "iPhone 8", "iPhone10,4" to "iPhone 8",
	"iPhone10,2" to "iPhone 8 Plus", "iPhone10,5" to "iPhone 8 Plus",
	"iPhone10,3" to "iPhone X", "iPhone10,6" to "iPhone X",
	"iPhone11,2" to "iPhone XS",
	"iPhone11,4" to "iPhone XS Max", "iPhone11,6" to "iPhone XS Max",
	"iPhone11,8" to "iPhone XR",
	"iPhone12,1" to "iPhone 11",
	"iPhone12,3" to "iPhone 11 Pro",
	"iPhone12,5" to "iPhone 11 Pro Max",
	"iPhone13,1" to "iPhone 12 mini",
	"iPhone13,2" to "iPhone 12",
	"iPhone13,3" to "iPhone 12 Pro",
	"iPhone13,4" to "iPhone 12 Pro Max",
	"iPhone14,4" to "iPhone 13 mini",
	"iPhone14,5" to "iPhone 13",
	"iPhone14,2" to "iPhone 13 Pro",
	"iPhone14,3" to "iPhone 13 Pro Max",
	"iPhone14,7" to "iPhone 14",
	"iPhone14,8" to "iPhone 14 Plus",
	"iPhone15,2" to "iPhone 14 Pro",
	"iPhone15,3" to "iPhone 14 Pro Max",
	"iPhone15,4" to "iPhone 15",
	"iPhone15,5" to "iPhone 15 Plus",
	"iPhone16,1" to "iPhone 15 Pro",
	"iPhone16,2" to "iPhone 15 Pro Max",
	"iPhone8,4" to "iPhone SE",
	"iPhone12,8" to "iPhone SE (2nd generation)",
	"iPhone14,6" to "iPhone SE (3rd generation)",
)
